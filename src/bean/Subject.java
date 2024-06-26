package bean;

// Schoolクラスを使用するためのインポート
// Schoolクラスが既に定義されていると仮定します
public class Subject {
    private String cd;     // 科目コード
    private String name;   // 科目名
    private School school; // 所属学校

    // コンストラクタ
    public Subject(String cd, String name, School school) {
        this.cd = cd;
        this.name = name;
        this.school = school;
    }

    // ゲッター (取得メソッド)
    public String getCd() {
        return cd;
    }

    public String getName() {
        return name;
    }

    public School getSchool() {
        return school;
    }

    // セッター (設定メソッド)
    public void setCd(String cd) {
        this.cd = cd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
