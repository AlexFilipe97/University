package edaI_T2;

public class Main
{
    public static void main(String[] args)
    {
        LineEditor editor = new LineEditor();

        editor.insertEnd("Texto qualquer");
        editor.insertEnd("Texto qualquer 2");
        editor.lineUp();
        editor.lineUp();
        editor.edit("Outra coisa");
        editor.delete(2);
        editor.insert(2, "Qualquer coisa");
        editor.insertEnd("Tanta coisa");
        editor.search("coisa");

        editor.print();
    }
}
