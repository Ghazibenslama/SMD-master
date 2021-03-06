package org.apromore.dao.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ProcessFragmentMap generated by hbm2java
 */
@Entity
@Table(name = "process_fragment_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Configurable("processFragmentMap")
public class ProcessFragmentMap implements Serializable {

    /**
     * Hard coded for interoperability.
     */
    private static final long serialVersionUID = -9072538487638485548L;

    private Integer mappingId;
    private FragmentVersion fragmentVersion;
    private ProcessModelVersion processModelVersion;


    public ProcessFragmentMap() { }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mapping_id", unique = true, nullable = false)
    public Integer getMappingId() {
        return this.mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fragment_version_id")
    public FragmentVersion getFragmentVersion() {
        return this.fragmentVersion;
    }

    public void setFragmentVersion(final FragmentVersion newFragmentVersion) {
        this.fragmentVersion = newFragmentVersion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_model_version_id")
    public ProcessModelVersion getProcessModelVersion() {
        return this.processModelVersion;
    }

    public void setProcessModelVersion(final ProcessModelVersion newProcessModelVersion) {
        this.processModelVersion = newProcessModelVersion;
    }

}


