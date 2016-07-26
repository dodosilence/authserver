package cc.moondust.authserver.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Tristan on 16/7/26.
 */
@Repository
public interface UserRepository {
    List<Map<String,Object>> selectAllUser() ;
}
