public class Pet {

    // Definindo uma classe chamada Pet para guardar a estrutura de dados sobre
    // animais(Pet)
    public int id; // Parâmetro do ID do pet

    public class Category { // Classe interna para representar a categoria do pet
        public int id; // Parâmetro do ID da categoria
        public String name; // Parâmetro do nome da categoria
    } // Fim da classe interna Category

    public Category category; // Parâmetro da categoria do pet

    public String name; // Parâmetro do nome do pet
    public String[] photoUrls; // Parâmetro das URLs das fotos do pet

    public class Tag { // Classe interna para representar as tags do pet
        public int id; // Parâmetro do ID da tag
        public String name; // Parâmetro do nome da tag
    } // Fim da classe interna Tag

    public Tag[] tags; // Parâmetro das tags do pet

    public String status; // Parâmetro do status do pet

}