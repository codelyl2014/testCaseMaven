package lyl.test;
/**
 * @author loong
 *
 */
public class VideoInfoVo {
	
	// 视频主标题
	private String title;
	// 视频副标题
	private String sub_title;
	// 视频介绍说明
	private String description;
	// 视频介绍说明(图片链接地址)
	private String description_url;
	// 视频链接地址
	private String video_url;
	// 视频缩略图链接地址
	private String thumbnail_url;
	// 视频时长(秒)
	private String video_time;
	// 视频渠道 1保利威视
	private String channel_type;
	// 视频渠道id
	private String channel_id;
	// 目录父级编码
	private String menu_father_code;
	// 目录编码
	private String menu_code;
	private String menu_name;
	// 目录层级
	private String menu_level;
	// 观看次数
	private String watch_count;
	// 观看次数+基数
	private String basic_watch_count;
	// 教师id
	private String teacher_id;
	private String teacher_name;
	// 微课一级目录【1.微课（正式） 2.新教师通识培训（试听）】
	private String menu_first;
	// 微课二级目录【 一级目录1：（1.专业类教学示范 2.通用类教师培训） 一级目录2：（1.通识培训 2.学科专业培训）】
	private String menu_second;
	// 微课三级目录【一级目录1 二级目录1：（1 优秀正式课参考 2 优秀试听课参考 3 优秀教学过程示范 4 优秀教学服务展示） 一级目录1 二级目录2：（1 教师资格证 2 名师分享讲座 3 新教师通识培训 4 互联网教师必备技能 5 其他） 一级目录2 二级目录1：（1 系统操作 2 制度讲解 ） 一级目录2 二级目录2：（1 讲义制作 2 课前沟通 3 授课过程 4 课尾沟通 5 课后跟进）】
	private String menu_third;
	// 微课四级目录【一级目录1 二级目录1 三级目录4：（1诊断报告 2.课后反馈 3.作业布置与批改 4.阶段反馈 5.师生沟通） 一级目录1 二级目录2 三级目录1：（1.年级 2.科目） 】
	private String menu_fourth;
	private String menu_fourth_second;
	// 微课五级目录【一级目录1 二级目录2 三级目录1 四级目录1: （1.小学 2.初中 3.高中） 一级目录1 二级目录2 三级目录1 四级目录2: （1.综合素质 2.教育知识与能力 3.学科教学知识与能力 4.面试）】
	private String menu_fifth;
	// 学科id
	private String subject_id;
	private String subject_name;
	private String grade_level;
	// 年级启
	private String grade_level_start;
	// 年级止
	private String grade_level_end;
	// 排序字段
	private String sort;
	// 是否显示推荐【 0 不显示  1 显示 】
	private String recommend;
	private String type;
	
	public String getMenu_fourth_second() {
		return menu_fourth_second;
	}
	public void setMenu_fourth_second(String menu_fourth_second) {
		this.menu_fourth_second = menu_fourth_second;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription_url() {
		return description_url;
	}
	public void setDescription_url(String description_url) {
		this.description_url = description_url;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getVideo_time() {
		return video_time;
	}
	public void setVideo_time(String video_time) {
		this.video_time = video_time;
	}
	public String getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getMenu_father_code() {
		return menu_father_code;
	}
	public void setMenu_father_code(String menu_father_code) {
		this.menu_father_code = menu_father_code;
	}
	public String getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level;
	}
	public String getWatch_count() {
		return watch_count;
	}
	public void setWatch_count(String watch_count) {
		this.watch_count = watch_count;
	}
	public String getBasic_watch_count() {
		return basic_watch_count;
	}
	public void setBasic_watch_count(String basic_watch_count) {
		this.basic_watch_count = basic_watch_count;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getMenu_first() {
		return menu_first;
	}
	public void setMenu_first(String menu_first) {
		this.menu_first = menu_first;
	}
	public String getMenu_second() {
		return menu_second;
	}
	public void setMenu_second(String menu_second) {
		this.menu_second = menu_second;
	}
	public String getMenu_third() {
		return menu_third;
	}
	public void setMenu_third(String menu_third) {
		this.menu_third = menu_third;
	}
	public String getMenu_fourth() {
		return menu_fourth;
	}
	public void setMenu_fourth(String menu_fourth) {
		this.menu_fourth = menu_fourth;
	}
	public String getMenu_fifth() {
		return menu_fifth;
	}
	public void setMenu_fifth(String menu_fifth) {
		this.menu_fifth = menu_fifth;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getGrade_level() {
		return grade_level;
	}
	public void setGrade_level(String grade_level) {
		this.grade_level = grade_level;
	}
	public String getGrade_level_start() {
		return grade_level_start;
	}
	public void setGrade_level_start(String grade_level_start) {
		this.grade_level_start = grade_level_start;
	}
	public String getGrade_level_end() {
		return grade_level_end;
	}
	public void setGrade_level_end(String grade_level_end) {
		this.grade_level_end = grade_level_end;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

}
