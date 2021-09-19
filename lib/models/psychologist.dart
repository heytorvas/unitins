class Psychologist {
  final int? id;
  final String name;
  final String cpf;
  final String birthDate;
  final String email;
  final String password;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  Psychologist(
    {this.id, required this.name, required this.cpf, required this.birthDate, required this.email, required this.password, this.createdAt, this.updatedAt}
  );

  @override
  String toString() {
    return 'Psychologist {id: $id, name: $name, cpf: $cpf, birthDate: $birthDate, email: $email, password: $password, createdAt: $createdAt, updatedAt: $updatedAt}';
  }
  
  Map<String, dynamic> toJson() {
    return {
      "name": this.name,
      "cpf": this.cpf,
      "birth_date": this.birthDate,
      "email": this.email,
      "password": this.password
    };
  }

  fromJson(Map<String, dynamic> parsedJson) {
    print(DateTime.parse(parsedJson['birth_date']));
    return Psychologist(
      id: parsedJson['id'],
      name: parsedJson['name'],
      cpf: parsedJson['cpf'],
      // birthDate: DateTime.parse(parsedJson['birth_date']),
      birthDate: parsedJson['birth_date'],
      email: parsedJson['email'],
      password : parsedJson['password'],
      createdAt: DateTime.parse(parsedJson['created_at']),
      updatedAt: DateTime.parse(parsedJson['updated_at'])
    );
  }
}