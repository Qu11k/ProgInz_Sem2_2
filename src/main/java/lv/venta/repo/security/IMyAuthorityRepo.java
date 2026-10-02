package lv.venta.repo.security;

import lv.venta.model.MyAuthority;
import org.springframework.data.repository.CrudRepository;

public interface IMyAuthorityRepo extends CrudRepository<MyAuthority, Long> {

}
