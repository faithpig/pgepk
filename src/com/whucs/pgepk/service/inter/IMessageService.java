package com.whucs.pgepk.service.inter;
import java.util.List;
import com.whucs.pgepk.hibernate.model.Message;

public interface IMessageService {
public List<Message> list(int offset,int pageSize);									//取出所有留言
public Message detail(String id);														//按id获取留言详细内容
public List<Message> search(String key,String column,int offset,int pageSize);		//搜索所有留言
public String add(String ip,String detail,String column);								//添加留言
public List<Message> listVerify(int offset,int pageSize);							//取出已审核留言
public List<Message> searchVerify(String key,String column,int offset,int pageSize);//搜索已审核留言
}
