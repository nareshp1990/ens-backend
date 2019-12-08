package com.ens.domain.entity;

import com.ens.config.content.JsonToMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Setter
@Getter
@Table(name = "content_info")
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class ContentInfo extends AuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "content_length", nullable = false)
    private long contentLength;

    @Column(name = "md5_check_sum", nullable = false)
    private String md5CheckSum;

    @Column(name = "is_encrypted", nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isEncrypted;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Type(type = "json")
    @Column(name = "meta_data", columnDefinition = "json")
    @Convert(attributeName = "data", converter = JsonToMapConverter.class)
    private Map<String, Object> metaData = new HashMap<>();

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User user;*/

}
