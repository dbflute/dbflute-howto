/*
 * Copyright 2014-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.howto.dbflute.bsbhv.pmbean;

import java.util.*;

import org.dbflute.outsidesql.typed.*;
import org.dbflute.jdbc.*;
import org.dbflute.cbean.coption.LikeSearchOption;
import org.dbflute.cbean.coption.FromToOption;
import org.dbflute.outsidesql.PmbCustodial;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.howto.dbflute.allcommon.*;
import org.docksidestage.howto.dbflute.exbhv.*;
import org.docksidestage.howto.dbflute.exentity.customize.*;

/**
 * The base class for typed parameter-bean of OptionMember. <br>
 * This is related to "<span style="color: #AD4747">selectOptionMember</span>" on MemberBhv.
 * @author DBFlute(AutoGenerator)
 */
public class BsOptionMemberPmb implements ListHandlingPmb<MemberBhv, OptionMember>, EntityHandlingPmb<MemberBhv, OptionMember>, FetchBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** The parameter of memberId. */
    protected Integer _memberId;

    /** The parameter of memberName:likePrefix. */
    protected String _memberName;

    /** The option of like-search for memberName. */
    protected LikeSearchOption _memberNameInternalLikeSearchOption;

    /** The parameter of memberAccount:like. */
    protected String _memberAccount;

    /** The option of like-search for memberAccount. */
    protected LikeSearchOption _memberAccountInternalLikeSearchOption;

    /** The parameter of fromFormalizedDate:fromDate. */
    protected java.time.LocalDate _fromFormalizedDate;

    /** The parameter of toFormalizedDate:toDate. */
    protected java.time.LocalDate _toFormalizedDate;

    /** The parameter of fromFormalizedMonth:fromDate(option). */
    protected java.time.LocalDateTime _fromFormalizedMonth;

    /** The parameter of toFormalizedMonth:toDate(option). */
    protected java.time.LocalDateTime _toFormalizedMonth;

    /** The parameter of memberStatusCode:ref(MEMBER) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus}. */
    protected String _memberStatusCode;

    /** The parameter of displayOrder:ref(MEMBER_STATUS) :: refers to (表示順)DISPLAY_ORDER: {UQ, NotNull, INTEGER(10)}. */
    protected Integer _displayOrder;

    /** The parameter of birthdate:fromDate|ref(MEMBER.BIRTHDATE) :: refers to (生年月日)BIRTHDATE: {DATE(10)}. */
    protected java.time.LocalDate _birthdate;

    /** The parameter of status:cls(MemberStatus). */
    protected String _status;

    /** The parameter of statusFormalized:cls(MemberStatus.Formalized). */
    protected String _statusFormalized = CDef.MemberStatus.Formalized.code();

    /** The parameter of statusList:ref(MEMBER.MEMBER_STATUS_CODE) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus}. */
    protected List<org.docksidestage.howto.dbflute.allcommon.CDef.MemberStatus> _statusList;

    /** The parameter of statusFixedList:cls(MemberStatus.Formalized, Withdrawal). */
    protected List<String> _statusFixedList = newArrayList(CDef.MemberStatus.Formalized.code(), CDef.MemberStatus.Withdrawal.code());

    /** The parameter of paymentCompleteFlg:cls(Flg). */
    protected Integer _paymentCompleteFlg;

    /** The parameter of paymentCompleteTrue:cls(Flg.True). */
    protected Integer _paymentCompleteTrue = toNumber(CDef.Flg.True.code(), Integer.class);

    /** The max size of safety result. */
    protected int _safetyMaxResultSize;

    /** The time-zone for filtering e.g. from-to. (NullAllowed: if null, default zone) */
    protected TimeZone _timeZone;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * Constructor for the typed parameter-bean of OptionMember. <br>
     * This is related to "<span style="color: #AD4747">selectOptionMember</span>" on MemberBhv.
     */
    public BsOptionMemberPmb() {
    }

    // ===================================================================================
    //                                                                Typed Implementation
    //                                                                ====================
    /**
     * {@inheritDoc}
     */
    public String getOutsideSqlPath() { return "selectOptionMember"; }

    /**
     * Get the type of an entity for result. (implementation)
     * @return The type instance of an entity, customize entity. (NotNull)
     */
    public Class<OptionMember> getEntityType() { return OptionMember.class; }

    // ===================================================================================
    //                                                                       Safety Result
    //                                                                       =============
    /**
     * {@inheritDoc}
     */
    public void checkSafetyResult(int safetyMaxResultSize) {
        _safetyMaxResultSize = safetyMaxResultSize;
    }

    /**
     * {@inheritDoc}
     */
    public int getSafetyMaxResultSize() {
        return _safetyMaxResultSize;
    }

    // ===================================================================================
    //                                                                       Assist Helper
    //                                                                       =============
    // -----------------------------------------------------
    //                                                String
    //                                                ------
    protected String filterStringParameter(String value) { return isEmptyStringParameterAllowed() ? value : convertEmptyToNull(value); }
    protected boolean isEmptyStringParameterAllowed() { return DBFluteConfig.getInstance().isEmptyStringParameterAllowed(); }
    protected String convertEmptyToNull(String value) { return PmbCustodial.convertEmptyToNull(value); }

    protected void assertLikeSearchOptionValid(String name, LikeSearchOption option) { PmbCustodial.assertLikeSearchOptionValid(name, option); }

    // -----------------------------------------------------
    //                                                  Date
    //                                                  ----
    protected Date toUtilDate(Object date) { return PmbCustodial.toUtilDate(date, _timeZone); }
    protected <DATE> DATE toLocalDate(Date date, Class<DATE> localType) { return PmbCustodial.toLocalDate(date, localType, chooseRealTimeZone()); }
    protected TimeZone chooseRealTimeZone() { return PmbCustodial.chooseRealTimeZone(_timeZone); }

    /**
     * Set time-zone, basically for LocalDate conversion. <br>
     * Normally you don't need to set this, you can adjust other ways. <br>
     * (DBFlute system's time-zone is used as default)
     * @param timeZone The time-zone for filtering. (NullAllowed: if null, default zone)
     */
    public void zone(TimeZone timeZone) { _timeZone = timeZone; }

    protected void assertFromToOptionValid(String name, FromToOption option) { PmbCustodial.assertFromToOptionValid(name, option); }
    protected FromToOption createFromToOption() { return PmbCustodial.createFromToOption(_timeZone); }

    // -----------------------------------------------------
    //                                    by Option Handling
    //                                    ------------------
    // might be called by option handling
    protected <NUMBER extends Number> NUMBER toNumber(Object obj, Class<NUMBER> type) { return PmbCustodial.toNumber(obj, type); }
    protected Boolean toBoolean(Object obj) { return PmbCustodial.toBoolean(obj); }
    @SuppressWarnings("unchecked")
    protected <ELEMENT> ArrayList<ELEMENT> newArrayList(ELEMENT... elements) { return PmbCustodial.newArrayList(elements); }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    /**
     * @return The display string of all parameters. (NotNull)
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(DfTypeUtil.toClassTitle(this)).append(":");
        sb.append(xbuildColumnString());
        return sb.toString();
    }
    protected String xbuildColumnString() {
        final String dm = ", ";
        final StringBuilder sb = new StringBuilder();
        sb.append(dm).append(_memberId);
        sb.append(dm).append(_memberName);
        sb.append(dm).append(_memberAccount);
        sb.append(dm).append(_fromFormalizedDate);
        sb.append(dm).append(_toFormalizedDate);
        sb.append(dm).append(_fromFormalizedMonth);
        sb.append(dm).append(_toFormalizedMonth);
        sb.append(dm).append(_memberStatusCode);
        sb.append(dm).append(_displayOrder);
        sb.append(dm).append(_birthdate);
        sb.append(dm).append(_status);
        sb.append(dm).append(_statusFormalized);
        sb.append(dm).append(_statusList);
        sb.append(dm).append(_statusFixedList);
        sb.append(dm).append(_paymentCompleteFlg);
        sb.append(dm).append(_paymentCompleteTrue);
        if (sb.length() > 0) { sb.delete(0, dm.length()); }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] memberId <br>
     * @return The value of memberId. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public Integer getMemberId() {
        return _memberId;
    }

    /**
     * [set] memberId <br>
     * @param memberId The value of memberId. (NullAllowed)
     */
    public void setMemberId(Integer memberId) {
        _memberId = memberId;
    }

    /**
     * [get] memberName:likePrefix <br>
     * @return The value of memberName. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public String getMemberName() {
        return filterStringParameter(_memberName);
    }

    /**
     * [set as prefixSearch] memberName:likePrefix <br>
     * @param memberName The value of memberName. (NullAllowed)
     */
    public void setMemberName_PrefixSearch(String memberName) {
        _memberName = memberName;
        _memberNameInternalLikeSearchOption = new LikeSearchOption().likePrefix();
    }

    /**
     * Get the internal option of likeSearch for memberName. {Internal Method: Don't invoke this}
     * @return The internal option of likeSearch for memberName. (NullAllowed)
     */
    public LikeSearchOption getMemberNameInternalLikeSearchOption() {
        return _memberNameInternalLikeSearchOption;
    }

    /**
     * [get] memberAccount:like <br>
     * @return The value of memberAccount. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public String getMemberAccount() {
        return filterStringParameter(_memberAccount);
    }

    /**
     * [set as likeSearch] memberAccount:like <br>
     * @param memberAccount The value of memberAccount. (NullAllowed)
     * @param memberAccountOption The option of likeSearch for memberAccount which is NOT split mode. (NotNull)
     */
    public void setMemberAccount(String memberAccount, LikeSearchOption memberAccountOption) {
        assertLikeSearchOptionValid("memberAccountOption", memberAccountOption);
        _memberAccount = memberAccount;
        _memberAccountInternalLikeSearchOption = memberAccountOption;
    }

    /**
     * Get the internal option of likeSearch for memberAccount. {Internal Method: Don't invoke this}
     * @return The internal option of likeSearch for memberAccount. (NullAllowed)
     */
    public LikeSearchOption getMemberAccountInternalLikeSearchOption() {
        return _memberAccountInternalLikeSearchOption;
    }

    /**
     * [get] fromFormalizedDate:fromDate <br>
     * @return The value of fromFormalizedDate. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public java.time.LocalDate getFromFormalizedDate() {
        return _fromFormalizedDate;
    }

    /**
     * [set as fromDate] fromFormalizedDate:fromDate <br>
     * @param fromFormalizedDate The value of fromFormalizedDate. (NullAllowed)
     */
    public void setFromFormalizedDate_FromDate(java.time.LocalDate fromFormalizedDate) {
        _fromFormalizedDate = toLocalDate(createFromToOption().compareAsDate().filterFromDate(toUtilDate(fromFormalizedDate)), java.time.LocalDate.class);
    }

    /**
     * [get] toFormalizedDate:toDate <br>
     * @return The value of toFormalizedDate. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public java.time.LocalDate getToFormalizedDate() {
        return _toFormalizedDate;
    }

    /**
     * [set as toDate] toFormalizedDate:toDate <br>
     * @param toFormalizedDate The value of toFormalizedDate. (NullAllowed)
     */
    public void setToFormalizedDate_ToDate(java.time.LocalDate toFormalizedDate) {
        _toFormalizedDate = toLocalDate(createFromToOption().compareAsDate().filterToDate(toUtilDate(toFormalizedDate)), java.time.LocalDate.class);
    }

    /**
     * [get] fromFormalizedMonth:fromDate(option) <br>
     * @return The value of fromFormalizedMonth. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public java.time.LocalDateTime getFromFormalizedMonth() {
        return _fromFormalizedMonth;
    }

    /**
     * [set as fromScope] fromFormalizedMonth:fromDate(option) <br>
     * @param fromFormalizedMonth The value of fromFormalizedMonth. (NullAllowed)
     * @param fromFormalizedMonthOption The option of from-to scope for fromFormalizedMonth. (NotNull)
     */
    public void setFromFormalizedMonth_FromDate(java.time.LocalDateTime fromFormalizedMonth, FromToOption fromFormalizedMonthOption) {
        assertFromToOptionValid("fromFormalizedMonthOption", fromFormalizedMonthOption);
        _fromFormalizedMonth = toLocalDate(fromFormalizedMonthOption.filterFromDate(toUtilDate(fromFormalizedMonth)), java.time.LocalDateTime.class);
    }

    /**
     * [get] toFormalizedMonth:toDate(option) <br>
     * @return The value of toFormalizedMonth. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public java.time.LocalDateTime getToFormalizedMonth() {
        return _toFormalizedMonth;
    }

    /**
     * [set as toScope] toFormalizedMonth:toDate(option) <br>
     * @param toFormalizedMonth The value of toFormalizedMonth. (NullAllowed)
     * @param toFormalizedMonthOption The option of from-to scope for toFormalizedMonth. (NotNull)
     */
    public void setToFormalizedMonth_ToDate(java.time.LocalDateTime toFormalizedMonth, FromToOption toFormalizedMonthOption) {
        assertFromToOptionValid("toFormalizedMonthOption", toFormalizedMonthOption);
        _toFormalizedMonth = toLocalDate(toFormalizedMonthOption.filterToDate(toUtilDate(toFormalizedMonth)), java.time.LocalDateTime.class);
    }

    /**
     * [get] memberStatusCode:ref(MEMBER) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * // reference option (including classification)
     * @return The value of memberStatusCode. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public String getMemberStatusCode() {
        return filterStringParameter(_memberStatusCode);
    }

    /**
     * [set as MemberStatus] memberStatusCode:ref(MEMBER) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * status of member from entry to withdrawal
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the property)
     */
    public void setMemberStatusCodeAsMemberStatus(CDef.MemberStatus cdef) {
        _memberStatusCode = cdef != null ? cdef.code() : null;
    }

    /**
     * [set as Formalized] memberStatusCode:ref(MEMBER) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * // reference option (including classification) <br>
     * as formal member, allowed to use all service
     */
    public void setMemberStatusCode_Formalized() {
        _memberStatusCode = CDef.MemberStatus.Formalized.code();
    }

    /**
     * [set as Withdrawal] memberStatusCode:ref(MEMBER) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * // reference option (including classification) <br>
     * withdrawal is fixed, not allowed to use service
     */
    public void setMemberStatusCode_Withdrawal() {
        _memberStatusCode = CDef.MemberStatus.Withdrawal.code();
    }

    /**
     * [set as Provisional] memberStatusCode:ref(MEMBER) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * // reference option (including classification) <br>
     * first status after entry, allowed to use only part of service
     */
    public void setMemberStatusCode_Provisional() {
        _memberStatusCode = CDef.MemberStatus.Provisional.code();
    }

    /**
     * [get] displayOrder:ref(MEMBER_STATUS) :: refers to (表示順)DISPLAY_ORDER: {UQ, NotNull, INTEGER(10)} <br>
     * @return The value of displayOrder. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public Integer getDisplayOrder() {
        return _displayOrder;
    }

    /**
     * [set] displayOrder:ref(MEMBER_STATUS) :: refers to (表示順)DISPLAY_ORDER: {UQ, NotNull, INTEGER(10)} <br>
     * @param displayOrder The value of displayOrder. (NullAllowed)
     */
    public void setDisplayOrder(Integer displayOrder) {
        _displayOrder = displayOrder;
    }

    /**
     * [get] birthdate:fromDate|ref(MEMBER.BIRTHDATE) :: refers to (生年月日)BIRTHDATE: {DATE(10)} <br>
     * // several options
     * @return The value of birthdate. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public java.time.LocalDate getBirthdate() {
        return _birthdate;
    }

    /**
     * [set as fromDate] birthdate:fromDate|ref(MEMBER.BIRTHDATE) :: refers to (生年月日)BIRTHDATE: {DATE(10)} <br>
     * // several options
     * @param birthdate The value of birthdate. (NullAllowed)
     */
    public void setBirthdate_FromDate(java.time.LocalDate birthdate) {
        _birthdate = toLocalDate(createFromToOption().compareAsDate().filterFromDate(toUtilDate(birthdate)), java.time.LocalDate.class);
    }

    /**
     * [get] status:cls(MemberStatus) <br>
     * // direct classification setting
     * @return The value of status. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public String getStatus() {
        return filterStringParameter(_status);
    }

    /**
     * [set as MemberStatus] status:cls(MemberStatus) <br>
     * status of member from entry to withdrawal
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the property)
     */
    public void setStatusAsMemberStatus(CDef.MemberStatus cdef) {
        _status = cdef != null ? cdef.code() : null;
    }

    /**
     * [set as Formalized] status:cls(MemberStatus) <br>
     * // direct classification setting <br>
     * as formal member, allowed to use all service
     */
    public void setStatus_Formalized() {
        _status = CDef.MemberStatus.Formalized.code();
    }

    /**
     * [set as Withdrawal] status:cls(MemberStatus) <br>
     * // direct classification setting <br>
     * withdrawal is fixed, not allowed to use service
     */
    public void setStatus_Withdrawal() {
        _status = CDef.MemberStatus.Withdrawal.code();
    }

    /**
     * [set as Provisional] status:cls(MemberStatus) <br>
     * // direct classification setting <br>
     * first status after entry, allowed to use only part of service
     */
    public void setStatus_Provisional() {
        _status = CDef.MemberStatus.Provisional.code();
    }

    /**
     * [get] statusFormalized:cls(MemberStatus.Formalized) <br>
     * // fixed classification setting
     * @return The value of statusFormalized. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public String getStatusFormalized() {
        return filterStringParameter(_statusFormalized);
    }

    /**
     * [get] statusList:ref(MEMBER.MEMBER_STATUS_CODE) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * // classification to list
     * @return The value of statusList. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public List<org.docksidestage.howto.dbflute.allcommon.CDef.MemberStatus> getStatusList() {
        return _statusList;
    }

    /**
     * [set] statusList:ref(MEMBER.MEMBER_STATUS_CODE) :: refers to (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to MEMBER_STATUS, classification=MemberStatus} <br>
     * // classification to list
     * @param statusList The value of statusList. (NullAllowed)
     */
    public void setStatusList(List<org.docksidestage.howto.dbflute.allcommon.CDef.MemberStatus> statusList) {
        _statusList = statusList;
    }

    /**
     * [get] statusFixedList:cls(MemberStatus.Formalized, Withdrawal) <br>
     * // fixed classification list
     * @return The value of statusFixedList. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public List<String> getStatusFixedList() {
        return _statusFixedList;
    }

    /**
     * [get] paymentCompleteFlg:cls(Flg) <br>
     * // direct one as Integer
     * @return The value of paymentCompleteFlg. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public Integer getPaymentCompleteFlg() {
        return _paymentCompleteFlg;
    }

    /**
     * [set as Flg] paymentCompleteFlg:cls(Flg) <br>
     * general boolean classification for every flg-column
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the property)
     */
    public void setPaymentCompleteFlgAsFlg(CDef.Flg cdef) {
        _paymentCompleteFlg = cdef != null ? toNumber(cdef.code(), Integer.class) : null;
    }

    /**
     * [set as True] paymentCompleteFlg:cls(Flg) <br>
     * // direct one as Integer <br>
     * means yes
     */
    public void setPaymentCompleteFlg_True() {
        _paymentCompleteFlg = toNumber(CDef.Flg.True.code(), Integer.class);
    }

    /**
     * [set as False] paymentCompleteFlg:cls(Flg) <br>
     * // direct one as Integer <br>
     * means no
     */
    public void setPaymentCompleteFlg_False() {
        _paymentCompleteFlg = toNumber(CDef.Flg.False.code(), Integer.class);
    }

    /**
     * [get] paymentCompleteTrue:cls(Flg.True) <br>
     * // fixed one as Integer
     * @return The value of paymentCompleteTrue. (NullAllowed, NotEmptyString(when String): if empty string, returns null)
     */
    public Integer getPaymentCompleteTrue() {
        return _paymentCompleteTrue;
    }
}
