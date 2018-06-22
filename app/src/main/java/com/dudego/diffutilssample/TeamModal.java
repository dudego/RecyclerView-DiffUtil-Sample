package com.dudego.diffutilssample;

public class TeamModal {

    private int id;
    private String likes;
    private String score;
    private String country;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamModal teamModal = (TeamModal) o;

        if (id != teamModal.id) return false;
        if (likes != null ? !likes.equals(teamModal.likes) : teamModal.likes != null) return false;
        if (score != null ? !score.equals(teamModal.score) : teamModal.score != null) return false;
        return country != null ? country.equals(teamModal.country) : teamModal.country == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
