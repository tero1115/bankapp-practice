package shop.mtcoding.bankapp.model.history;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryRepository {
    public int insert(History user);

    public int updateById(History user);

    public int deleteById(int id);

    public List<History> findAll();

    public History findById(int id);
}