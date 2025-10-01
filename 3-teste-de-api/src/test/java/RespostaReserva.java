public class RespostaReserva {
    private int bookingid;
    private Reserva booking;

    public RespostaReserva() {}

    public RespostaReserva(int idReserva, Reserva reserva) {
        this.bookingid = idReserva;
        this.booking = reserva;
    }

    // Getters e Setters originais (mantém compatibilidade com JSON)
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int idReserva) {
        this.bookingid = idReserva;
    }

    public Reserva getBooking() {
        return booking;
    }

    public void setBooking(Reserva reserva) {
        this.booking = reserva;
    }

    // Getters e Setters em português (para uso interno)
    public int getIdReserva() {
        return bookingid;
    }

    public void setIdReserva(int idReserva) {
        this.bookingid = idReserva;
    }

    public Reserva getReserva() {
        return booking;
    }

    public void setReserva(Reserva reserva) {
        this.booking = reserva;
    }

    @Override
    public String toString() {
        return "RespostaReserva{" +
                "idReserva=" + bookingid +
                ", reserva=" + booking +
                '}';
    }
}