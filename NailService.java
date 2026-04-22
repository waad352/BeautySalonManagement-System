class NailService extends Service {
    public NailService() {
        super("Nails ", 60.0);
    }
    public String getDetails() {
        return getName() + " - SAR " + getPrice();
    }
}
