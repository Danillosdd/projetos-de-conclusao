public class RespostaAutenticacao {
    private String token;
    private String reason;

    public RespostaAutenticacao() {}

    public RespostaAutenticacao(String token) {
        this.token = token;
    }

    public RespostaAutenticacao(String token, String motivo) {
        this.token = token;
        this.reason = motivo;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String motivo) {
        this.reason = motivo;
    }

    public String getMotivo() {
        return reason;
    }

    public void setMotivo(String motivo) {
        this.reason = motivo;
    }

    @Override
    public String toString() {
        return "RespostaAutenticacao{" +
                "token='" + (token != null ? "[TOKEN_PRESENTE]" : "null") + '\'' +
                ", motivo='" + reason + '\'' +
                '}';
    }
}