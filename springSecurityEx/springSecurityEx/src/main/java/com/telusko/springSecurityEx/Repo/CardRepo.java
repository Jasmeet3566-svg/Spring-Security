
package com.telusko.springSecurityEx.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import com.telusko.springSecurityEx.model.Card;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {
    
    
	
    List<Card> findByUsername(String userName);
}


//package com.telusko.springSecurityEx.Repo;
//
//import java.util.List;
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.ListCrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.telusko.springSecurityEx.model.Card;
//
//@Repository
//
//public interface CardRepo extends ListCrudRepository<Card, Integer> {
//    
//	@Query("""
//	         SELECT applicationName FROM card WHERE username = 'vijay'; ;
// """)
//    List<Card> findAllByUsername();
//    
//}
