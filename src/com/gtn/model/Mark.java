package com.gtn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MARK")
public class Mark implements Model
{
  private String id;
  private String document_filename;
  private String document_relative_path;
  private String selection_text;
  private int has_selection;
  private String color;
  private String selection_info;
  private int readonly;
  private String type;
  private String displayFormat;
  
  
  public Mark() {}
  private String note;
  private int pageIndex;
  private float positionX;
  private float positionY;
  private int width;
  

	@Id
	@Column(name = "ID")
  public String getId()
  {
    return id;
  }
  private int height;
  
  public void setId(String id)
  {
    this.id = id;
  }
  private int collapsed;
  
  @Column(name = "Document_filename")
  public String getDocument_filename()
  {
    return document_filename;
  }
  
  public void setDocument_filename(String document_filename)
  {
    this.document_filename = document_filename;
  }
  
  private String points;
  
  @Column(name = "Document_relative_path")
  public String getDocument_relative_path()
  {
    return document_relative_path;
  }
  
  private String datecreated;
  
  public void setDocument_relative_path(String document_relative_path)
  {
    this.document_relative_path = document_relative_path;
  }
  
  private String datechanged;
  
  @Column(name = "Selection_text")
  public String getSelection_text()
  {
    return selection_text;
  }
  
  private String author;
  public void setSelection_text(String selection_text)
  {
    this.selection_text = selection_text;
  }
  
  @Column(name = "Has_selection")
  public int getHas_selection()
  {
    return has_selection;
  }
  
  public void setHas_selection(int has_selection)
  {
    this.has_selection = has_selection;
  }
  
  @Column(name = "Color")
  public String getColor()
  {
    return color;
  }
  
  public void setColor(String color)
  {
    this.color = color;
  }
  @Column(name = "Selection_info")
  public String getSelection_info()
  {
    return selection_info;
  }
  
  public void setSelection_info(String selection_info)
  {
    this.selection_info = selection_info;
  }
  
  @Column(name = "Readonly")
  public int getReadonly()
  {
    return readonly;
  }
  
  public void setReadonly(int readonly)
  {
    this.readonly = readonly;
  }
  @Column(name = "Type")
  public String getType()
  {
    return type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  @Column(name = "DisplayFormat")
  public String getDisplayFormat()
  {
    return displayFormat;
  }
  
  public void setDisplayFormat(String displayFormat)
  {
    this.displayFormat = displayFormat;
  }
  
  @Column(name = "Note")
  public String getNote()
  {
    return note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  @Column(name = "PageIndex")
  public int getPageIndex()
  {
    return pageIndex;
  }
  
  public void setPageIndex(int pageIndex)
  {
    this.pageIndex = pageIndex;
  }
  
  @Column(name = "PositionX")
  public float getPositionX()
  {
    return positionX;
  }
  
  public void setPositionX(float positionX)
  {
    this.positionX = positionX;
  }
  
  @Column(name = "PositionY")
  public float getPositionY()
  {
    return positionY;
  }
  
  public void setPositionY(float positionY)
  {
    this.positionY = positionY;
  }
  @Column(name = "Width")
  public int getWidth()
  {
    return width;
  }
  
  public void setWidth(int width)
  {
    this.width = width;
  }
  @Column(name = "Height")
  public int getHeight()
  {
    return height;
  }
  
  public void setHeight(int height)
  {
    this.height = height;
  }
  @Column(name = "Collapsed")
  public int getCollapsed()
  {
    return collapsed;
  }
  
  public void setCollapsed(int collapsed)
  {
    this.collapsed = collapsed;
  }
  
  @Column(name = "points")
  public String getPoints()
  {
    return points;
  }
  
  public void setPoints(String points)
  {
    this.points = points;
  }
  @Column(name = "Datecreated")
  public String getDatecreated()
  {
    return datecreated;
  }
  
  public void setDatecreated(String datecreated)
  {
    this.datecreated = datecreated;
  }
  @Column(name = "Datechanged")  
  public String getDatechanged()
  {
    return datechanged;
  }
  
  public void setDatechanged(String datechanged)
  {
    this.datechanged = datechanged;
  }
  @Column(name = "Author")
  public String getAuthor()
  {
    return author;
  }
  
  public void setAuthor(String author)
  {
    this.author = author;
  }
}