package com.ens.domain.entity.content;

import com.ens.config.content.JsonToMapConverter;
import com.ens.domain.entity.audit.DateAudit;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Table(name = "content_info")
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class ContentInfo extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
