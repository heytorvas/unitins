class Login {
  final String email;
  final String password;

  Login({required this.email, required this.password});

  @override
  String toString() {
    return 'Login {email: $email, password: $password}';
  }

  Map<String, dynamic> toJson() {
    return {
      "email": this.email,
      "password": this.password
    };
  }

  factory Login.fromJson(Map<String, dynamic> parsedJson) {
    return Login(
      email: parsedJson['email'],
      password : parsedJson['password']
    );
  }
}