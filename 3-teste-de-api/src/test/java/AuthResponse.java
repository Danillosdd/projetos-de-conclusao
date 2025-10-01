public class AuthResponse {
    private String token;

    // Construtor padrão
    public AuthResponse() {}

    // Construtor com parâmetros
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}