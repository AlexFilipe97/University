import socket, sys, select

TCP_IP = '127.0.0.1'
TCP_PORT = 5000
BUFFER_SIZE = 4096


def prompt():
    sys.stdout.write('\n$ ')
    sys.stdout.flush()


def getinput():
    while 1:
        msg = sys.stdin.readline()
        msg = msg.strip()
        if msg != "":
            break
        else:
            print("Mandatory field\n")
    return msg


def whichinput(command):
    msg = command.split()

    if len(msg) is 1:
        print("\nCommand not found.\n")
        prompt()
    elif '"' in msg[1]:
        other_msg = command.split('"')
        name = other_msg[1]
        data = "GETNUMBER {}".format(name)
    elif '"' not in msg[1] and len(msg) is 2:
        number = msg[1]
        data = "REVERSE {}".format(number)
    elif "-set" in msg and '"' in msg[2]:
        other_msg = command.split('"')
        name = other_msg[1]
        number = other_msg[2]
        data = "SETNUMBER {} {}".format(name, number)
    elif "-del" in msg and len(msg) is 3:
        other_msg = command.split('"')
        name = other_msg[1]
        data = "DELETECLIENT {}".format(name)
    elif "-del" in msg and len(msg) is 4 and '"' in msg[3]:
        other_msg = command.split('"')
        name = other_msg[1]
        data = "DELETECLIENT {}".format(name)
    elif "-del" in msg and len(msg) is 4 and '"' not in msg[3]:
        other_msg = command.split('"')
        name = other_msg[1]
        number = other_msg[2]
        data = "DELETENUMBER {} {}".format(name, number)
    elif "-del" in msg and len(msg) is 5 and '"' not in msg[4]:
        other_msg = command.split('"')
        name = other_msg[1]
        number = other_msg[2]
        data = "DELETENUMBER {} {}".format(name, number)
    else:
        print("\nCommand not found.\n")
        prompt()
    return data


def output(data):
    msg = data.split()

    if msg[0] == "NUMBERSET":
        other_msg = data.split('"')
        print(other_msg[1] + " number set to " + other_msg[2])
    elif msg[0] == "CLIENTHASNUMBERS":
        other_msg = data.split('"')
        print(other_msg[1] + " has numbers" + other_msg[2])
    elif msg[0] == "CLIENTHASNAMES":
        other_msg = data.split('"')
        print(msg[1] + " is the number for " + other_msg[1])
    elif msg[0] == "DELETED":
        other_msg = data.split('"')
        if len(msg) is 4 and '"' not in msg[3]:
            print(other_msg[1] + " number " + msg[3] + " deleted from database")
        elif len(msg) is 3 and '"' not in msg[2]:
            print(other_msg[1] + " number " + msg[2] + " deleted from database")
        else:
            print(other_msg[1] + " deleted from database")


def main():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((TCP_IP, TCP_PORT))
    prompt()
    while True:
        socket_list = [sys.stdin, s]

        # Espera por um socket com dados (ou stdin)
        read_sockets, write_sockets, error_sockets = select.select(socket_list, [], [])

        for sock in read_sockets:
            # chegou uma mensagem no socket
            if sock == s:
                data = sock.recv(4096).decode()
                if not data:
                    print('\nDisconnected from remote server')
                    sys.exit()
                else:
                    output(data)
                    #sys.stdout.write("\n" + data + "\n")
                    #sys.stdout.flush()
                    prompt()
            # mensagem escrita no teclado
            else:
                msg = getinput()
                msg = whichinput(msg)
                s.send(msg.encode())

if __name__ == "__main__":
    main()