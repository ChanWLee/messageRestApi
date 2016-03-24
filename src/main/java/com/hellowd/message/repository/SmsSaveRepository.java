package com.hellowd.message.repository;

import com.hellowd.message.entity.SmsSave;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Helloworld
 * User : chanwoo.lee
 * Date : 2015-11-20
 * Time : 오전 11:44
 * 해당 클래스에 대한 기능 설명
 */
public interface SmsSaveRepository extends JpaRepository<SmsSave, Long>{
}
