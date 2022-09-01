package chapter16.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年09月01日
 */
@Data
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;
}
