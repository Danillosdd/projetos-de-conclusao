public class Reserva {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private DatasReserva bookingdates;
    private String additionalneeds;

    public Reserva() {}

    public Reserva(String primeiroNome, String ultimoNome, int precoTotal, boolean depositoPago, 
                   DatasReserva datasReserva, String necessidadesAdicionais) {
        this.firstname = primeiroNome;
        this.lastname = ultimoNome;
        this.totalprice = precoTotal;
        this.depositpaid = depositoPago;
        this.bookingdates = datasReserva;
        this.additionalneeds = necessidadesAdicionais;
    }

    // Getters e Setters originais (mantém compatibilidade com JSON)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String primeiroNome) {
        this.firstname = primeiroNome;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String ultimoNome) {
        this.lastname = ultimoNome;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int precoTotal) {
        this.totalprice = precoTotal;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositoPago) {
        this.depositpaid = depositoPago;
    }

    public DatasReserva getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(DatasReserva datasReserva) {
        this.bookingdates = datasReserva;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String necessidadesAdicionais) {
        this.additionalneeds = necessidadesAdicionais;
    }

    // Getters e Setters em português (para uso interno)
    public String getPrimeiroNome() {
        return firstname;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.firstname = primeiroNome;
    }

    public String getUltimoNome() {
        return lastname;
    }

    public void setUltimoNome(String ultimoNome) {
        this.lastname = ultimoNome;
    }

    public int getPrecoTotal() {
        return totalprice;
    }

    public void setPrecoTotal(int precoTotal) {
        this.totalprice = precoTotal;
    }

    public boolean isDepositoPago() {
        return depositpaid;
    }

    public void setDepositoPago(boolean depositoPago) {
        this.depositpaid = depositoPago;
    }

    public DatasReserva getDatasReserva() {
        return bookingdates;
    }

    public void setDatasReserva(DatasReserva datasReserva) {
        this.bookingdates = datasReserva;
    }

    public String getNecessidadesAdicionais() {
        return additionalneeds;
    }

    public void setNecessidadesAdicionais(String necessidadesAdicionais) {
        this.additionalneeds = necessidadesAdicionais;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "primeiroNome='" + firstname + '\'' +
                ", ultimoNome='" + lastname + '\'' +
                ", precoTotal=" + totalprice +
                ", depositoPago=" + depositpaid +
                ", datasReserva=" + bookingdates +
                ", necessidadesAdicionais='" + additionalneeds + '\'' +
                '}';
    }
}