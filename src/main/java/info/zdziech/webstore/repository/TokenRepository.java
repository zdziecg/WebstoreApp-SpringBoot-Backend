package info.zdziech.webstore.repository;


import info.zdziech.webstore.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface TokenRepository extends JpaRepository<Token, Long> {


        Token findByValue(String value);

    }
