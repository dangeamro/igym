package com.designer.model.statusAndMeasurements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.designer.common.framework.ApplicationConfig;

public class AutomatedMeasurements {
	
	private Date date_Time;
	private String id;
	private Double icw;
	private Double icw_Min;
	private Double icw_max;
	private Double ecw;
	private Double ecw_min;
	private Double ecw_max;
	private Double protein;
	private Double protein_Min;
	
	private Double protein_Max;
	private Double mineral;
	private Double mineral_Min;
	private Double mineral_Max;
	private Double fat;
	private Double fat_Min;
	private Double fat_Max;
	private Double tbw;
	private Double LeanBodyMass;
	private Double weight;
	
	private Double Ideal_Icw;
	private Double Ideal_Ecw;
	private Double ideal_Protein;
	private Double ideal_mineral;
	private Double ideal_Fat;
	private Double Ideal_Tbw;
	private Double ecwTbw;
	private Double lbmRightArm;
	private Double pcntLbmRightArm;
	private Double secondGraphRightArm;
	
	private Double lbmLeftArm;
	private Double pcntLbmLeftArm;
	private Double secondGraphLeftArm;
	private Double lbmTrunk;
	private Double pcntLbmTrunk;
	private Double secondGraphTrunk;
	private Double lbmRightLeg;
	private Double pcntLbmRightLeg;
	private Double secondGraphRightLeg;
	private Double lbmLeftLeg;
	
	private Double pcntLbmLeftLeg;
	private Double secondGraphLeftLeg;
	private Double pcntBodyWeight;
	private Double wt_Min;
	private Double wt_Max;
	private Double pcntBodyFat;
	private Double ideal_Weight;
	private Double pcntFat;
	private Double pcntTbw;
	private Double idealPcntBodyFat;
	
	private Double pcntLeanBodyMass;
	private Double idealLeanBodyMass;
	private Double pbf_Min1;
	private Double pbf_Max1;
	private Double bmi;
	private Double bmi_Min;
	private Double bmi_Max;
	private Double pbf;
	private Double pbf_Min2;
	private Double pbf_Max2;
	
	private Double idealBmi;
	private Double height;
	private Double age;
	private String sex;
	private Double basalMetabolicRate;
	
	/**
	 * @return the age
	 */
	public Double getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Double age) {
		this.age = age;
	}
	/**
	 * @return the basalMetabolicRate
	 */
	public Double getBasalMetabolicRate() {
		return basalMetabolicRate;
	}
	/**
	 * @param basalMetabolicRate the basalMetabolicRate to set
	 */
	public void setBasalMetabolicRate(Double basalMetabolicRate) {
		this.basalMetabolicRate = basalMetabolicRate;
	}
	/**
	 * @return the bmi
	 */
	public Double getBmi() {
		return bmi;
	}
	/**
	 * @param bmi the bmi to set
	 */
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	/**
	 * @return the bmi_Max
	 */
	public Double getBmi_Max() {
		return bmi_Max;
	}
	/**
	 * @param bmi_Max the bmi_Max to set
	 */
	public void setBmi_Max(Double bmi_Max) {
		this.bmi_Max = bmi_Max;
	}
	/**
	 * @return the bmi_Min
	 */
	public Double getBmi_Min() {
		return bmi_Min;
	}
	/**
	 * @param bmi_Min the bmi_Min to set
	 */
	public void setBmi_Min(Double bmi_Min) {
		this.bmi_Min = bmi_Min;
	}
	/**
	 * @return the date_Time
	 */
	public String getDate_Time() {
		DateFormat df = new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("dateTimePattern"));
		return df.format(date_Time);
	}
	public Date getDate_TimeAsDate() {		
		return date_Time;
	}
	/**
	 * @param date_Time the date_Time to set
	 */
	public void setDate_Time(Date date_Time) {
		this.date_Time = date_Time;
	}
	/**
	 * @return the ecw
	 */
	public Double getEcw() {
		return ecw;
	}
	/**
	 * @param ecw the ecw to set
	 */
	public void setEcw(Double ecw) {
		this.ecw = ecw;
	}
	/**
	 * @return the ecw_max
	 */
	public Double getEcw_max() {
		return ecw_max;
	}
	/**
	 * @param ecw_max the ecw_max to set
	 */
	public void setEcw_max(Double ecw_max) {
		this.ecw_max = ecw_max;
	}
	/**
	 * @return the ecw_min
	 */
	public Double getEcw_min() {
		return ecw_min;
	}
	/**
	 * @param ecw_min the ecw_min to set
	 */
	public void setEcw_min(Double ecw_min) {
		this.ecw_min = ecw_min;
	}
	/**
	 * @return the ecwTbw
	 */
	public Double getEcwTbw() {
		return ecwTbw;
	}
	/**
	 * @param ecwTbw the ecwTbw to set
	 */
	public void setEcwTbw(Double ecwTbw) {
		this.ecwTbw = ecwTbw;
	}
	/**
	 * @return the fat
	 */
	public Double getFat() {
		return fat;
	}
	/**
	 * @param fat the fat to set
	 */
	public void setFat(Double fat) {
		this.fat = fat;
	}
	/**
	 * @return the fat_Max
	 */
	public Double getFat_Max() {
		return fat_Max;
	}
	/**
	 * @param fat_Max the fat_Max to set
	 */
	public void setFat_Max(Double fat_Max) {
		this.fat_Max = fat_Max;
	}
	/**
	 * @return the fat_Min
	 */
	public Double getFat_Min() {
		return fat_Min;
	}
	/**
	 * @param fat_Min the fat_Min to set
	 */
	public void setFat_Min(Double fat_Min) {
		this.fat_Min = fat_Min;
	}
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * @return the icw
	 */
	public Double getIcw() {
		return icw;
	}
	/**
	 * @param icw the icw to set
	 */
	public void setIcw(Double icw) {
		this.icw = icw;
	}
	/**
	 * @return the icw_max
	 */
	public Double getIcw_max() {
		return icw_max;
	}
	/**
	 * @param icw_max the icw_max to set
	 */
	public void setIcw_max(Double icw_max) {
		this.icw_max = icw_max;
	}
	/**
	 * @return the icw_Min
	 */
	public Double getIcw_Min() {
		return icw_Min;
	}
	/**
	 * @param icw_Min the icw_Min to set
	 */
	public void setIcw_Min(Double icw_Min) {
		this.icw_Min = icw_Min;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the ideal_Ecw
	 */
	public Double getIdeal_Ecw() {
		return Ideal_Ecw;
	}
	/**
	 * @param ideal_Ecw the ideal_Ecw to set
	 */
	public void setIdeal_Ecw(Double ideal_Ecw) {
		Ideal_Ecw = ideal_Ecw;
	}
	/**
	 * @return the ideal_Fat
	 */
	public Double getIdeal_Fat() {
		return ideal_Fat;
	}
	/**
	 * @param ideal_Fat the ideal_Fat to set
	 */
	public void setIdeal_Fat(Double ideal_Fat) {
		this.ideal_Fat = ideal_Fat;
	}
	/**
	 * @return the ideal_Icw
	 */
	public Double getIdeal_Icw() {
		return Ideal_Icw;
	}
	/**
	 * @param ideal_Icw the ideal_Icw to set
	 */
	public void setIdeal_Icw(Double ideal_Icw) {
		Ideal_Icw = ideal_Icw;
	}
	/**
	 * @return the ideal_mineral
	 */
	public Double getIdeal_mineral() {
		return ideal_mineral;
	}
	/**
	 * @param ideal_mineral the ideal_mineral to set
	 */
	public void setIdeal_mineral(Double ideal_mineral) {
		this.ideal_mineral = ideal_mineral;
	}
	/**
	 * @return the ideal_Protein
	 */
	public Double getIdeal_Protein() {
		return ideal_Protein;
	}
	/**
	 * @param ideal_Protein the ideal_Protein to set
	 */
	public void setIdeal_Protein(Double ideal_Protein) {
		this.ideal_Protein = ideal_Protein;
	}
	/**
	 * @return the ideal_Tbw
	 */
	public Double getIdeal_Tbw() {
		return Ideal_Tbw;
	}
	/**
	 * @param ideal_Tbw the ideal_Tbw to set
	 */
	public void setIdeal_Tbw(Double ideal_Tbw) {
		Ideal_Tbw = ideal_Tbw;
	}
	/**
	 * @return the ideal_Weight
	 */
	public Double getIdeal_Weight() {
		return ideal_Weight;
	}
	/**
	 * @param ideal_Weight the ideal_Weight to set
	 */
	public void setIdeal_Weight(Double ideal_Weight) {
		this.ideal_Weight = ideal_Weight;
	}
	/**
	 * @return the idealBmi
	 */
	public Double getIdealBmi() {
		return idealBmi;
	}
	/**
	 * @param idealBmi the idealBmi to set
	 */
	public void setIdealBmi(Double idealBmi) {
		this.idealBmi = idealBmi;
	}
	/**
	 * @return the idealLeanBodyMass
	 */
	public Double getIdealLeanBodyMass() {
		return idealLeanBodyMass;
	}
	/**
	 * @param idealLeanBodyMass the idealLeanBodyMass to set
	 */
	public void setIdealLeanBodyMass(Double idealLeanBodyMass) {
		this.idealLeanBodyMass = idealLeanBodyMass;
	}
	/**
	 * @return the idealPcntBodyFat
	 */
	public Double getIdealPcntBodyFat() {
		return idealPcntBodyFat;
	}
	/**
	 * @param idealPcntBodyFat the idealPcntBodyFat to set
	 */
	public void setIdealPcntBodyFat(Double idealPcntBodyFat) {
		this.idealPcntBodyFat = idealPcntBodyFat;
	}
	/**
	 * @return the lbmLeftArm
	 */
	public Double getLbmLeftArm() {
		return lbmLeftArm;
	}
	/**
	 * @param lbmLeftArm the lbmLeftArm to set
	 */
	public void setLbmLeftArm(Double lbmLeftArm) {
		this.lbmLeftArm = lbmLeftArm;
	}
	/**
	 * @return the lbmLeftLeg
	 */
	public Double getLbmLeftLeg() {
		return lbmLeftLeg;
	}
	/**
	 * @param lbmLeftLeg the lbmLeftLeg to set
	 */
	public void setLbmLeftLeg(Double lbmLeftLeg) {
		this.lbmLeftLeg = lbmLeftLeg;
	}
	/**
	 * @return the lbmRightArm
	 */
	public Double getLbmRightArm() {
		return lbmRightArm;
	}
	/**
	 * @param lbmRightArm the lbmRightArm to set
	 */
	public void setLbmRightArm(Double lbmRightArm) {
		this.lbmRightArm = lbmRightArm;
	}
	/**
	 * @return the lbmRightLeg
	 */
	public Double getLbmRightLeg() {
		return lbmRightLeg;
	}
	/**
	 * @param lbmRightLeg the lbmRightLeg to set
	 */
	public void setLbmRightLeg(Double lbmRightLeg) {
		this.lbmRightLeg = lbmRightLeg;
	}
	/**
	 * @return the lbmTrunk
	 */
	public Double getLbmTrunk() {
		return lbmTrunk;
	}
	/**
	 * @param lbmTrunk the lbmTrunk to set
	 */
	public void setLbmTrunk(Double lbmTrunk) {
		this.lbmTrunk = lbmTrunk;
	}
	/**
	 * @return the leanBodyMass
	 */
	public Double getLeanBodyMass() {
		return LeanBodyMass;
	}
	/**
	 * @param leanBodyMass the leanBodyMass to set
	 */
	public void setLeanBodyMass(Double leanBodyMass) {
		LeanBodyMass = leanBodyMass;
	}
	/**
	 * @return the mineral
	 */
	public Double getMineral() {
		return mineral;
	}
	/**
	 * @param mineral the mineral to set
	 */
	public void setMineral(Double mineral) {
		this.mineral = mineral;
	}
	/**
	 * @return the mineral_Max
	 */
	public Double getMineral_Max() {
		return mineral_Max;
	}
	/**
	 * @param mineral_Max the mineral_Max to set
	 */
	public void setMineral_Max(Double mineral_Max) {
		this.mineral_Max = mineral_Max;
	}
	/**
	 * @return the mineral_Min
	 */
	public Double getMineral_Min() {
		return mineral_Min;
	}
	/**
	 * @param mineral_Min the mineral_Min to set
	 */
	public void setMineral_Min(Double mineral_Min) {
		this.mineral_Min = mineral_Min;
	}
	/**
	 * @return the pbf
	 */
	public Double getPbf() {
		return pbf;
	}
	/**
	 * @param pbf the pbf to set
	 */
	public void setPbf(Double pbf) {
		this.pbf = pbf;
	}
	/**
	 * @return the pbf_Max1
	 */
	public Double getPbf_Max1() {
		return pbf_Max1;
	}
	/**
	 * @param pbf_Max1 the pbf_Max1 to set
	 */
	public void setPbf_Max1(Double pbf_Max1) {
		this.pbf_Max1 = pbf_Max1;
	}
	/**
	 * @return the pbf_Max2
	 */
	public Double getPbf_Max2() {
		return pbf_Max2;
	}
	/**
	 * @param pbf_Max2 the pbf_Max2 to set
	 */
	public void setPbf_Max2(Double pbf_Max2) {
		this.pbf_Max2 = pbf_Max2;
	}
	/**
	 * @return the pbf_Min1
	 */
	public Double getPbf_Min1() {
		return pbf_Min1;
	}
	/**
	 * @param pbf_Min1 the pbf_Min1 to set
	 */
	public void setPbf_Min1(Double pbf_Min1) {
		this.pbf_Min1 = pbf_Min1;
	}
	/**
	 * @return the pbf_Min2
	 */
	public Double getPbf_Min2() {
		return pbf_Min2;
	}
	/**
	 * @param pbf_Min2 the pbf_Min2 to set
	 */
	public void setPbf_Min2(Double pbf_Min2) {
		this.pbf_Min2 = pbf_Min2;
	}
	/**
	 * @return the pcntBodyFat
	 */
	public Double getPcntBodyFat() {
		return pcntBodyFat;
	}
	/**
	 * @param pcntBodyFat the pcntBodyFat to set
	 */
	public void setPcntBodyFat(Double pcntBodyFat) {
		this.pcntBodyFat = pcntBodyFat;
	}
	/**
	 * @return the pcntBodyWeight
	 */
	public Double getPcntBodyWeight() {
		return pcntBodyWeight;
	}
	/**
	 * @param pcntBodyWeight the pcntBodyWeight to set
	 */
	public void setPcntBodyWeight(Double pcntBodyWeight) {
		this.pcntBodyWeight = pcntBodyWeight;
	}
	/**
	 * @return the pcntFat
	 */
	public Double getPcntFat() {
		return pcntFat;
	}
	/**
	 * @param pcntFat the pcntFat to set
	 */
	public void setPcntFat(Double pcntFat) {
		this.pcntFat = pcntFat;
	}
	/**
	 * @return the pcntLbmLeftArm
	 */
	public Double getPcntLbmLeftArm() {
		return pcntLbmLeftArm;
	}
	/**
	 * @param pcntLbmLeftArm the pcntLbmLeftArm to set
	 */
	public void setPcntLbmLeftArm(Double pcntLbmLeftArm) {
		this.pcntLbmLeftArm = pcntLbmLeftArm;
	}
	/**
	 * @return the pcntLbmLeftLeg
	 */
	public Double getPcntLbmLeftLeg() {
		return pcntLbmLeftLeg;
	}
	/**
	 * @param pcntLbmLeftLeg the pcntLbmLeftLeg to set
	 */
	public void setPcntLbmLeftLeg(Double pcntLbmLeftLeg) {
		this.pcntLbmLeftLeg = pcntLbmLeftLeg;
	}
	/**
	 * @return the pcntLbmRightArm
	 */
	public Double getPcntLbmRightArm() {
		return pcntLbmRightArm;
	}
	/**
	 * @param pcntLbmRightArm the pcntLbmRightArm to set
	 */
	public void setPcntLbmRightArm(Double pcntLbmRightArm) {
		this.pcntLbmRightArm = pcntLbmRightArm;
	}
	/**
	 * @return the pcntLbmRightLeg
	 */
	public Double getPcntLbmRightLeg() {
		return pcntLbmRightLeg;
	}
	/**
	 * @param pcntLbmRightLeg the pcntLbmRightLeg to set
	 */
	public void setPcntLbmRightLeg(Double pcntLbmRightLeg) {
		this.pcntLbmRightLeg = pcntLbmRightLeg;
	}
	/**
	 * @return the pcntLbmTrunk
	 */
	public Double getPcntLbmTrunk() {
		return pcntLbmTrunk;
	}
	/**
	 * @param pcntLbmTrunk the pcntLbmTrunk to set
	 */
	public void setPcntLbmTrunk(Double pcntLbmTrunk) {
		this.pcntLbmTrunk = pcntLbmTrunk;
	}
	/**
	 * @return the pcntLeanBodyMass
	 */
	public Double getPcntLeanBodyMass() {
		return pcntLeanBodyMass;
	}
	/**
	 * @param pcntLeanBodyMass the pcntLeanBodyMass to set
	 */
	public void setPcntLeanBodyMass(Double pcntLeanBodyMass) {
		this.pcntLeanBodyMass = pcntLeanBodyMass;
	}
	/**
	 * @return the pcntTbw
	 */
	public Double getPcntTbw() {
		return pcntTbw;
	}
	/**
	 * @param pcntTbw the pcntTbw to set
	 */
	public void setPcntTbw(Double pcntTbw) {
		this.pcntTbw = pcntTbw;
	}
	/**
	 * @return the protein
	 */
	public Double getProtein() {
		return protein;
	}
	/**
	 * @param protein the protein to set
	 */
	public void setProtein(Double protein) {
		this.protein = protein;
	}
	/**
	 * @return the protein_Max
	 */
	public Double getProtein_Max() {
		return protein_Max;
	}
	/**
	 * @param protein_Max the protein_Max to set
	 */
	public void setProtein_Max(Double protein_Max) {
		this.protein_Max = protein_Max;
	}
	/**
	 * @return the protein_Min
	 */
	public Double getProtein_Min() {
		return protein_Min;
	}
	/**
	 * @param protein_Min the protein_Min to set
	 */
	public void setProtein_Min(Double protein_Min) {
		this.protein_Min = protein_Min;
	}
	/**
	 * @return the secondGraphLeftArm
	 */
	public Double getSecondGraphLeftArm() {
		return secondGraphLeftArm;
	}
	/**
	 * @param secondGraphLeftArm the secondGraphLeftArm to set
	 */
	public void setSecondGraphLeftArm(Double secondGraphLeftArm) {
		this.secondGraphLeftArm = secondGraphLeftArm;
	}
	/**
	 * @return the secondGraphLeftLeg
	 */
	public Double getSecondGraphLeftLeg() {
		return secondGraphLeftLeg;
	}
	/**
	 * @param secondGraphLeftLeg the secondGraphLeftLeg to set
	 */
	public void setSecondGraphLeftLeg(Double secondGraphLeftLeg) {
		this.secondGraphLeftLeg = secondGraphLeftLeg;
	}
	/**
	 * @return the secondGraphRightArm
	 */
	public Double getSecondGraphRightArm() {
		return secondGraphRightArm;
	}
	/**
	 * @param secondGraphRightArm the secondGraphRightArm to set
	 */
	public void setSecondGraphRightArm(Double secondGraphRightArm) {
		this.secondGraphRightArm = secondGraphRightArm;
	}
	/**
	 * @return the secondGraphRightLeg
	 */
	public Double getSecondGraphRightLeg() {
		return secondGraphRightLeg;
	}
	/**
	 * @param secondGraphRightLeg the secondGraphRightLeg to set
	 */
	public void setSecondGraphRightLeg(Double secondGraphRightLeg) {
		this.secondGraphRightLeg = secondGraphRightLeg;
	}
	/**
	 * @return the secondGraphTrunk
	 */
	public Double getSecondGraphTrunk() {
		return secondGraphTrunk;
	}
	/**
	 * @param secondGraphTrunk the secondGraphTrunk to set
	 */
	public void setSecondGraphTrunk(Double secondGraphTrunk) {
		this.secondGraphTrunk = secondGraphTrunk;
	}
	
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the tbw
	 */
	public Double getTbw() {
		return tbw;
	}
	/**
	 * @param tbw the tbw to set
	 */
	public void setTbw(Double tbw) {
		this.tbw = tbw;
	}
	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * @return the wt_Max
	 */
	public Double getWt_Max() {
		return wt_Max;
	}
	/**
	 * @param wt_Max the wt_Max to set
	 */
	public void setWt_Max(Double wt_Max) {
		this.wt_Max = wt_Max;
	}
	/**
	 * @return the wt_Min
	 */
	public Double getWt_Min() {
		return wt_Min;
	}
	/**
	 * @param wt_Min the wt_Min to set
	 */
	public void setWt_Min(Double wt_Min) {
		this.wt_Min = wt_Min;
	}
	
			
	


}
