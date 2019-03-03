package com.dev.bruno.insuranceapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Module {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    private BigDecimal minimumCoverage;

    @Min(0)
    private BigDecimal maximumCoverage;

    @Min(0)
    private BigDecimal risk;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private Set<Price> prices;

    public Module() {
    }

    public Module(@NotBlank String name, @Min(0) BigDecimal minimumCoverage, @Min(0) BigDecimal maximumCoverage, @Min(0) BigDecimal risk) {
        this.name = name;
        this.minimumCoverage = minimumCoverage;
        this.maximumCoverage = maximumCoverage;
        this.risk = risk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinimumCoverage() {
        return minimumCoverage;
    }

    public void setMinimumCoverage(BigDecimal minimumCoverage) {
        this.minimumCoverage = minimumCoverage;
    }

    public BigDecimal getMaximumCoverage() {
        return maximumCoverage;
    }

    public void setMaximumCoverage(BigDecimal maximumCoverage) {
        this.maximumCoverage = maximumCoverage;
    }

    public BigDecimal getRisk() {
        return risk;
    }

    public void setRisk(BigDecimal risk) {
        this.risk = risk;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }
}
