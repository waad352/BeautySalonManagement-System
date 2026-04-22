class SkinCareService extends Service {
    public SkinCareService() {
        super("Skin Care ", 100.0);
    }
    public String getDetails() {
        return getName() + " - SAR " + getPrice();
    }
}
