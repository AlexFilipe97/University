# -*- coding: utf-8 -*-
import socket, select, json
import traceback  # para informação de excepções

SOCKET_LIST = []  # lista de sockets abertos
RECV_BUFFER = 4096  # valor recomendado na doc. do python
PORT = 5000
agenda = {}

def readFile():
    global agenda
    try:
        agenda = json.load(open("lista_telefonica.txt"))
    except:
        print("Criado ficheiro com contactos.")


def writeFile():
    json.dump(agenda, open("lista_telefonica.txt", "w"))


def SETNUMBER(data):
    if len(data) is 3:
        name = data[1]
        number = data[2]
    else:
        name = data[1] + " " + data[2]
        number = data[3]

    if name in agenda:
        agenda[name].append(number)
    else:
        agenda[name] = [number]
    writeFile()


# função que trata dados do cliente
def operacao(data, sock):
        msg = data.split()
        readFile()

        if msg[0] == "SETNUMBER":
            SETNUMBER(msg)
            if len(msg) is 3:
                name = msg[1]
                number = msg[2]
            else:
                name = msg[1] + " " + msg[2]
                number = msg[3]
            answer = "NUMBERSET" + " \"" + name + "\" " + number
            sock.send(answer.encode())
        elif msg[0] == "GETNUMBER":
            if len(msg) is 2:
                name = msg[1]
            else:
                name = msg[1] + " " + msg[2]
            if name not in agenda:
                answer = "NOTFOUND " + name
            else:
                number = agenda[name]
                answer = "CLIENTHASNUMBERS " + "\"" + name + "\""
                for numbers in number:
                    answer = answer + " " + numbers
            sock.send(answer.encode())
        elif msg[0] == "REVERSE":
            wanted = msg[1]
            names = []
            for key, value in agenda.items():
                for v in value:
                    if v == wanted:
                        names.append(key)
            if names == []:
                answer = "NOTFOUND " + wanted
            else:
                answer = " CLIENTHASNAMES " + wanted + " \""
                for name in names:
                    answer = answer + name + ", "
            sock.send(answer.encode())
        elif msg[0] == "DELETECLIENT":
            if len(msg) is 3:
                name = msg[1] + " " + msg[2]
            else:
                name = msg[1]
            if name not in agenda:
                answer = "NOTFOUND " + name
            else:
                del agenda[name]
                writeFile()
                answer = "DELETED \"" + name + "\""
            sock.send(answer.encode())
        elif msg[0] == "DELETENUMBER":
            if len(msg) is 3:
                name = msg[1]
                number = msg[2]
            else:
                name = msg[1] + " " + msg[2]
                number = msg[3]
            empty_numbers = []
            removed = None

            if name not in agenda:
                answer = "NOTFOUND " + name
            else:
                for key, value in agenda.items():
                    if key == name:
                        for v in value:
                            if v == number:
                                value.remove(v)
                                removed = True
                                if agenda[key] == []:
                                    empty_numbers.append(key)
                writeFile()
                readFile()
                for word in empty_numbers:
                    del agenda[word]
                writeFile()
                if not removed:
                    answer = "NOTFOUND " + number
                else:
                    answer = "DELETED \"" + name + "\" " + number
            sock.send(answer.encode())


if __name__ == "__main__":
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind(("0.0.0.0", PORT))  # aceita ligações de qualquer lado
    server_socket.listen(10)
    server_socket.setblocking(0)  # o socket deixa de ser blocking

    # Adicionamos o socket à lista de sockets a monitorizar
    SOCKET_LIST.append(server_socket)

    print("Server started on port %d" % (PORT,))

    timecount = 0
    while True:  # ciclo infinito

        # apagamos os sockets que "morreram" entretanto
        for sock in SOCKET_LIST:
            if sock.fileno() < 0:
                SOCKET_LIST.remove(sock)

        # agora esperamos que haja dados em algum dos sockets que temos
        rsocks, _, _ = select.select(SOCKET_LIST, [], [], 5)

        if len(rsocks) == 0:  # timeout
            timecount += 5
            print("Timeout on select() -> %d secs" % (timecount))

            if timecount % 60 == 0:  # passou um minuto
                timecount = 0
            continue

        for sock in rsocks:  # percorrer os sockets com nova informação

            if sock == server_socket:  # há uma nova ligação
                newsock, addr = server_socket.accept()
                newsock.setblocking(0)
                SOCKET_LIST.append(newsock)

                print("New client - %s" % (addr,))

            else:  # há dados num socket ligado a um cliente
                try:
                    data = sock.recv(RECV_BUFFER).decode()
                    if data:
                        operacao(data, sock)

                    else:  # não há dados, o cliente fechou o socket
                        print("Client disconnected 1")
                        sock.close()
                        SOCKET_LIST.remove(sock)

                except Exception as e:  # excepção ao ler o socket, o cliente fechou ou morreu
                    print("Client disconnected")
                    print("Exception -> %s" % (e,))
                    print(traceback.format_exc())

                    sock.close()
                    SOCKET_LIST.remove(sock)