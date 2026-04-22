class MakeupService extends Service {
    public MakeupService() {
        super("Makeup ", 120.0);
    }
    public String getDetails() {
        return getName() + " - SAR " + getPrice();
    }
}
