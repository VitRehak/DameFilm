package cz.uhk.fim.DameFilm.entity.movie;

public enum AgeRating {
    GeneralAudiences(0),
    ParentsStronglyCautioned(7),
    ParentalGuidanceSuggested(12),
    Restricted(16),
    AdultsOnly(18);

    private final int age;

    AgeRating(int age) {
        this.age = age;
    }
    public int getAge(){
        return age;
    }
}
