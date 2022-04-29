package cz.uhk.fim.DameFilm.security;

public interface PermissionEvaluator {

    boolean canEditUser(Object principal, long requestId);

    boolean canEditMovie(Object principal, long requestId);

}
