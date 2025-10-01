public class DatasReserva {
    private String checkin;
    private String checkout;

    public DatasReserva() {}

    public DatasReserva(String dataCheckin, String dataCheckout) {
        this.checkin = dataCheckin;
        this.checkout = dataCheckout;
    }

    // Getters e Setters
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String dataCheckin) {
        this.checkin = dataCheckin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String dataCheckout) {
        this.checkout = dataCheckout;
    }

    public String getDataCheckin() {
        return checkin;
    }

    public void setDataCheckin(String dataCheckin) {
        this.checkin = dataCheckin;
    }

    public String getDataCheckout() {
        return checkout;
    }

    public void setDataCheckout(String dataCheckout) {
        this.checkout = dataCheckout;
    }

    @Override
    public String toString() {
        return "DatasReserva{" +
                "dataCheckin='" + checkin + '\'' +
                ", dataCheckout='" + checkout + '\'' +
                '}';
    }
}