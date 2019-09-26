package com.company;

import com.univocity.parsers.annotations.Parsed;

public class SapEntity {
//   1
//Уникальный идентификатор кредитного договора
//Строка (8)
//Да

  //Уникальный идентификатор кредитного договора в АС «СД»
  @Parsed(index = 0)
  String asSDid;
  //2
//Номер
//Строка (20)
//Нет
  //Номер договора.
//Не может состоять только из одного символа «#» («решетка») и не может начинаться с символа «!» (восклицательный знак»).
  @Parsed(index = 1)
  String contractNumber;
  //3
//Код вида обязательств Заемщика
//Строка (1)
//Да
//Код вида обязательств Заемщика.
//Определяется из уникального идентификатора кредитного договора в АС «СД». Второй символ.
//Значение должно соответствовать справочнику «Вид обязательства».
  @Parsed(index = 2)
  String typeCode;
  //4
//Дата начала действия договора
//Дата (8)
//Да
//Дата начала действия договора
//  20110603
  @Parsed(index = 3)
  String openDate;
  //5
//Дата закрытия договора
//Дата (8)
//Нет
//Дата закрытия договора
  @Parsed(index = 4)
  String closeDate;
  //6
//Сумма/лимит по договору
//Число (14,2)
//Да
//Сумма/Лимит по договору в валюте учета.
//Всегда должен быть больше нуля.
  @Parsed(index = 5)
  String limit;
  //7
//Валюта учета
//Строка (3)
//Да
//Валюта учета по договору.
//Трехсимвольный буквенный код в формате ISO.
  @Parsed(index = 6)
  String currency;
  //8
//Срочная задолженность в валюте учета
//Число  (14,2)
//Да
//Срочная задолженность в валюте учета
  @Parsed(index = 7)
  String urgentDept;
  //9
//Просроченная задолженность в валюте учета
//Число (14,2)
//Да
//Просроченная задолженность в валюте учета
  @Parsed(index = 8)
  String overdueDept;
  //10
//Неиспользованный (невыбранный) лимит в валюте учета
//Число (14,2)
//Да
//Неиспользованный (невыбранный) лимит в валюте учета
  @Parsed(index = 9)
  String unusedLimit;
  //11
//Полное наименование юридического лица
//Строка (1020)
//Да
//Полное наименование юридического лица
  @Parsed(index = 10)
  String jurFullName;
  //12
//Фамилия
//Строка (60)
//Да
//Фамилия
  @Parsed(index = 11)
  String surname;
  //13
//Имя
//Строка (60)
//Да
//Имя
  @Parsed(index = 12)
  String name;
  //14
//Отчество
//Строка (60)
//Да
//Отчество
  @Parsed(index = 13)
  String secondName;
  //15
//ИНН/КИО
//Строка (10)
//Да
//ИНН/КИО заемщика
  @Parsed(index = 14)
  String inn;
  //16
//КПП
//Строка (9)
//См. описание
//КПП заемщика.
  @Parsed(index = 15)
  String kpp;

  public String getAsSDid() {
    return asSDid;
  }

  public void setAsSDid(String asSDid) {
    this.asSDid = asSDid;
  }

  public String getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(String contractNumber) {
    this.contractNumber = contractNumber;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getOpenDate() {
    return openDate;
  }

  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }

  public String getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(String closeDate) {
    this.closeDate = closeDate;
  }

  public String getLimit() {
    return limit;
  }

  public void setLimit(String limit) {
    this.limit = limit;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getUrgentDept() {
    return urgentDept;
  }

  public void setUrgentDept(String urgentDept) {
    this.urgentDept = urgentDept;
  }

  public String getOverdueDept() {
    return overdueDept;
  }

  public void setOverdueDept(String overdueDept) {
    this.overdueDept = overdueDept;
  }

  public String getUnusedLimit() {
    return unusedLimit;
  }

  public void setUnusedLimit(String unusedLimit) {
    this.unusedLimit = unusedLimit;
  }

  public String getJurFullName() {
    return jurFullName;
  }

  public void setJurFullName(String jurFullName) {
    this.jurFullName = jurFullName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getInn() {
    return inn;
  }

  public void setInn(String inn) {
    this.inn = inn;
  }

  public String getKpp() {
    return kpp;
  }

  public void setKpp(String kpp) {
    this.kpp = kpp;
  }

  @Override
  public String toString() {
    return "SapEntity{" +
        "asSDid='" + asSDid + '\'' + "\n" +
        ", contractNumber='" + contractNumber + '\'' +"\n" +
        ", typeCode='" + typeCode + '\'' +"\n" +
        ", openDate='" + openDate + '\'' +"\n" +
        ", closeDate='" + closeDate + '\'' +"\n" +
        ", limit='" + limit + '\'' +"\n" +
        ", currency='" + currency + '\'' +"\n" +
        ", urgentDept='" + urgentDept + '\'' +"\n" +
        ", overdueDept='" + overdueDept + '\'' +"\n" +
        ", unusedLimit='" + unusedLimit + '\'' +"\n" +
        ", jurFullName='" + jurFullName + '\'' +"\n" +
        ", surname='" + surname + '\'' +"\n" +
        ", name='" + name + '\'' +"\n" +
        ", secondName='" + secondName + '\'' +"\n" +
        ", inn='" + inn + '\'' +"\n" +
        ", kpp='" + kpp + '\'' +"\n" +
        '}';
  }
}
/**/
