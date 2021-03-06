package com.mybatis3.mappers;

import com.mybatis3.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


/**
 * @author Siva
 *
 */
public interface StudentMapper
{
	
	@Select("select * from students")
	@Results({
			@Result(id=true, column="stud_id", property="studId"),
			@Result(column="name", property="name"),
			@Result(column="email", property="email"),
			@Result(column="addr_id", property="address.addrId")			
	})
	List<Student> findAllStudents();

	@Select("select stud_id as studId, name, email, addr_id as 'address.addrId', phone from students")
	List<Map<String,Object>> findAllStudentsMap();
	
	@Select("select stud_id as studId, name, email, addr_id as 'address.addrId', phone from students where stud_id=#{id}")
    Student findStudentById(Integer id);
	
	@Select("select stud_id as studId, name, email, addr_id as 'address.addrId', phone from students where stud_id=#{id}")
	Map<String,Object> findStudentMapById(Integer id);

	@Select("select stud_id, name, email, a.addr_id, street, city, state, zip, country"+
  		" FROM students s left outer join addresses a on s.addr_id=a.addr_id"+
		" where stud_id=#{studId} ")
	@ResultMap("com.mybatis3.mappers.StudentMapper.StudentWithAddressResult")
    Student selectStudentWithAddress(int studId);
	
	@Insert("insert into students(name,email,addr_id, phone) values(#{name},#{email},#{address.addrId},#{phone})")
	@Options(useGeneratedKeys=true, keyProperty="studId")
	void insertStudent(Student student);
	
	@Insert("insert into students(name,email,addr_id, phone) values(#{name},#{email},#{address.addrId},#{phone})")
	@Options(useGeneratedKeys=true, keyProperty="studId")
	void insertStudentWithMap(Map<String, Object> map);

	@Update("update students set name=#{name}, email=#{email}, phone=#{phone} where stud_id=#{studId}")
	void updateStudent(Student student);
	
	@Delete("delete from students where stud_id=#{studId}")
	int deleteStudent(int studId);
	
}
