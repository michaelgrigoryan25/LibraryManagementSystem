public enum Genre {
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    MYSTERY("Mystery"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    HISTORICAL_FICTION("Historical Fiction"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    MEMOIR("Memoir"),
    SELF_HELP("Self-Help"),
    BUSINESS("Business"),
    HISTORY("History"),
    PHILOSOPHY("Philosophy"),
    PSYCHOLOGY("Psychology"),
    SOCIOLOGY("Sociology"),
    SCIENCE("Science"),
    MATHEMATICS("Mathematics"),
    TECHNOLOGY("Technology"),
    ART("Art"),
    MUSIC("Music"),
    SPORTS("Sports"),
    TRAVEL("Travel"),
    COOKING("Cooking"),
    CRAFTS_AND_HOBBIES("Crafts and Hobbies"),
    POETRY("Poetry"),
    DRAMA("Drama"),
    COMICS("Comics"),
    GRAPHIC_NOVEL("Graphic Novel"),
    CHILDRENS("Children's"),
    YOUNG_ADULT("Young Adult"),
    ADULT("Adult"),
    EDUCATIONAL("Educational"),
    REFERENCE("Reference"),
    RELIGION_AND_SPIRITUALITY("Religion and Spirituality"),
    POLITICS("Politics"),
    CURRENT_EVENTS("Current Events"),
    LAW("Law"),
    CRIME("Crime"),
    HEALTH_AND_FITNESS("Health and Fitness"),
    DIET_AND_NUTRITION("Diet and Nutrition"),
    PARENTING("Parenting"),
    FAMILY_AND_RELATIONSHIPS("Family and Relationships"),
    EDUCATION("Education"),
    LANGUAGE_AND_LINGUISTICS("Language and Linguistics"),
    ECONOMICS("Economics"),
    ENVIRONMENT_AND_ECOLOGY("Environment and Ecology");

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
