package lv.venta.repo.security;
import org.springframework.data.repository.CrudRepository;
import lv.venta.model.MyUser;

public interface IMyUserRepo extends CrudRepository<MyUser, Long> {

}
