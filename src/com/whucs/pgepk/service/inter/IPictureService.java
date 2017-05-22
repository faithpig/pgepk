package com.whucs.pgepk.service.inter;
import java.util.List;
import com.whucs.pgepk.hibernate.model.Picture;

public interface IPictureService {
public List<Picture> get(String type,String wzid);			//�������id������ȡͼƬ 
}
