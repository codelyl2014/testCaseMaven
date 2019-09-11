package lyl.test;

public class BookInfoVo {

    // 唯一标识码
    private String id;

    // 学科ID
    private String subject_id;

    // 年级编号,以逗号分隔
    private String grade_code;

    // 年级描述
    private String grade;
    
    // 书名
    private String name;
    
    // 作者
    private String author;

    // 出版社
    private String publisher;

    // 推荐指数
    private String recommend_index;

    // 推荐理由
    private String recommend_reason;

    // 图书的封面url
    private String cover;

    // 适用教材
    private String apply_teach;

    // 是否存有电子版
    private String is_elec_version;

    // 电子版URL
    private String elec_url;

    // 更新时间
    private String update_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	public String getGrade_code() {
		return grade_code;
	}

	public void setGrade_code(String grade_code) {
		this.grade_code = grade_code;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getRecommend_index() {
		return recommend_index;
	}

	public void setRecommend_index(String recommend_index) {
		this.recommend_index = recommend_index;
	}

	public String getRecommend_reason() {
		return recommend_reason;
	}

	public void setRecommend_reason(String recommend_reason) {
		this.recommend_reason = recommend_reason;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getApply_teach() {
		return apply_teach;
	}

	public void setApply_teach(String apply_teach) {
		this.apply_teach = apply_teach;
	}

	public String getIs_elec_version() {
		return is_elec_version;
	}

	public void setIs_elec_version(String is_elec_version) {
		this.is_elec_version = is_elec_version;
	}

	public String getElec_url() {
		return elec_url;
	}

	public void setElec_url(String elec_url) {
		this.elec_url = elec_url;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
 
}
