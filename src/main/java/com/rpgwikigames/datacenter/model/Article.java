package com.rpgwikigames.datacenter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rpgwikigames.datacenter.util.QuotedArrayEnserializer;
import com.rpgwikigames.datacenter.util.UnixTimestampDeserializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Article")
//allowGetters=true khi client gửi lên createAt và UpdateAt sẽ không được set vì mặc định allowSetter=false, chỉ trả về khi response cho client khi allowGetter=true, mặc định sẽ là false nếu không muốn trả 2 giá trị này về cho client bằng rest json
@JsonIgnoreProperties(ignoreUnknown = true, value = { "createdAt", "updatedAt", "id" }, allowGetters = true)
//AuditingEntityListener Bật tính năng tự động thêm hoặc update thời gian @LastModifiedDate @CreatedDate
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@SequenceGenerator(name = "seq", initialValue = 1000)
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
	private Long id;

	@Size(max = 100)
	@Column(unique = true)
	@JsonProperty("title")
	private String title;

	private String position;

	@JsonProperty("summary")
	private String summary;

	@Column(nullable = false, length = 10000)
	@JsonProperty("description")
	private String content;

	@Lob
	@JsonProperty("descriptionHTML")
	private String descriptionHTML;

	@JsonProperty("appId")
	private String appStoreId;

	@JsonProperty("icon")
	private String iconUrl;

	@Column(columnDefinition = "LONGBLOB")
	@JsonProperty("screenshots")
	private String[] screenshotUrls;

	@JsonProperty("score")
	private float score;

	@JsonProperty("scoreDetail")
	@JsonSerialize(using = QuotedArrayEnserializer.class)
	private float[] scoreDetail;

	@JsonProperty("url")
	private String playStoreUrl;

	@JsonProperty("headerImage")
	private String headerImage;

	@JsonProperty("downloads")
	private String downloads;

	@JsonProperty("ratings")
	private int allRatingCount;

	@JsonProperty("reviews")
	private int allReviewsCount;

	@JsonProperty("minInstalls")
	private int minInstalls;

	@JsonProperty("installs")
	private String installs;

	@JsonProperty("video")
	private String video;

	@JsonProperty("updated")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	private Date updated;

	@JsonProperty("size")
	private String fileSize;

	@JsonProperty("developer")
	private String developer;

	@JsonProperty("developerId")
	private String developerId;

	@Column(name = "createdAt", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP) // @Temporal chuyển đổi các giá trị ngày và thời gian từ Đối tượng Java thành
										// loại cơ sở dữ liệu tương thích và ngược lại.
	@CreatedDate // Tự động thêm vào thời gian khi tạo
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate // Tự động sửa thời gian khi update
	private Date updatedAt;
}
