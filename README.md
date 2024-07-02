User :  là dối tượng để chứa các thông tin của người dùng như là tài khoản,mật khẩu,role,...
code demo:
//public class User {
    private String username;
    private String password;
    private List<String> roles;
}//

UserDetail : là nơi hiển thị thông tin về người dùng đã cung cấp trước đó
Code demo : 
//
public class CustomUserDetails implements UserDetails {
    User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
//

UserDetailsService :  là chứa các câu lệnh thay đổi các xử lí thông tin của người dùng 
Code demo: 
//
@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy email phù hợp"));
    }
//

PasswordEncoder :  dùng để mã hoá mật khẩu và so sánh mật khẩu
Code demo : 
//
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//

AuthenticationProvide : là phương thức để xác thực người dùng
Code demo: 
//
public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
//

SecurityContextHolder : là dùng để lưu trữ các thông tin của người dùng đang truy cập
Code demo : 
//
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String currentPrincipalName = authentication.getName();
//
