
package bean;

public class User {
    /**
     * 認証済みフラグ:boolean true:認証済み
     */
    private boolean isAuthenticated;
    private School school;

    /**
     * ゲッター、セッター
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
