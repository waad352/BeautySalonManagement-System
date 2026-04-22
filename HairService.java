class HairService extends Service {
    public HairService() {
        super("Hair Styling ", 80.0);
    }
    public String getDetails() {
        return getName() + " - SAR " + getPrice();
    }
}
