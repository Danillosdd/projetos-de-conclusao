public class SolicitacaoAutenticacao {
    private String username;
    private String password;

    public SolicitacaoAutenticacao() {}

    public SolicitacaoAutenticacao(String nomeUsuario, String senha) {
        this.username = nomeUsuario;
        this.password = senha;
    }

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String nomeUsuario) {
        this.username = nomeUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public String getNomeUsuario() {
        return username;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.username = nomeUsuario;
    }

    public String getSenha() {
        return password;
    }

    public void setSenha(String senha) {
        this.password = senha;
    }

    @Override
    public String toString() {
        return "SolicitacaoAutenticacao{" +
                "nomeUsuario='" + username + '\'' +
                ", senha='[PROTEGIDA]'" +
                '}';
    }
}