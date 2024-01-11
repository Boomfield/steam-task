package steam.pages.helpers;

public enum Language {
    ENGLISH("English", "en"),
    RUSSIAN("Русский", "ru");

    private final String displayName;
    private final String languageCode;

    Language(String displayName, String languageCode) {
        this.displayName = displayName;
        this.languageCode = languageCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public static Language getByCodeName(String codeName) {
        for (Language language : Language.values()) {
            if (language.getLanguageCode().equalsIgnoreCase(codeName)) {
                return language;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + codeName);
    }
}
