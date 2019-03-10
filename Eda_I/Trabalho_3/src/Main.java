public class Main
{
    public static void main(String[] args)
    {
        /*
        AgendaTelemovel agenda = new AgendaTelemovel();

        AgendaTelemovel.Contacto p1 = new AgendaTelemovel.Contacto("João", 923456758);
        AgendaTelemovel.Contacto p2 = new AgendaTelemovel.Contacto("Ana", 915874569);

        agenda.adicionar(p1);
        agenda.adicionar(p2);
        agenda.chamador(915874569);
        agenda.editar(p1, 943572145, 1);
        agenda.editar(p2, "Zezinha");
        agenda.remover(p1);

        agenda.listar();
        */
        /*
        AgendaTelemovel agenda = new AgendaTelemovel();

        AgendaTelemovel.Contacto pessoa1 = new AgendaTelemovel.Contacto("Joao", 985214563);
        AgendaTelemovel.Contacto pessoa2 = new AgendaTelemovel.Contacto("Ana", 253485221);
        AgendaTelemovel.Contacto pessoa3 = new AgendaTelemovel.Contacto("Ze", 452558958);
        AgendaTelemovel.Contacto pessoa4 = new AgendaTelemovel.Contacto("Emanuel", 958623547);
        AgendaTelemovel.Contacto pessoa5 = new AgendaTelemovel.Contacto("Beatriz", 914587236);
        AgendaTelemovel.Contacto pessoa6 = new AgendaTelemovel.Contacto("Rui", 574123698);
        AgendaTelemovel.Contacto pessoa7 = new AgendaTelemovel.Contacto("Fernando", 258963214);

        agenda.adicionar(pessoa1);
        agenda.adicionar(pessoa2);
        agenda.adicionar(pessoa3);
        agenda.adicionar(pessoa4);
        agenda.adicionar(pessoa5);
        agenda.adicionar(pessoa6);
        agenda.adicionar(pessoa7);
        agenda.editar(pessoa1, 958421789, 1);
        agenda.editar(pessoa1, -1, 0);
        agenda.editar(pessoa2, 314221563, 1);
        agenda.editar(pessoa2, 789456132, 0);
        agenda.editar(pessoa2, "Ricardo");
        agenda.chamador(574123698);
        agenda.listar();
        */

        ArvoreBinariaPesquisa<Integer> abp = new ArvoreBinariaPesquisa<Integer>(10);

        abp.insere(5);
        abp.insere(3);
        abp.insere(7);
        abp.insere(15);
        abp.insere(13);
        abp.insere(17);
        abp.insere(17);
        abp.printEmOrdem();

    }
}
