import 'package:flutter/rendering.dart';

class Psychologist {
  int? id;
  String? name;
  String? cpf;
  String? birthDate;
  String? email;
  String? password;
  DateTime? createdAt;
  DateTime? updatedAt;

  Psychologist(
    {this.id, this.name, this.cpf, this.birthDate, this.email, this.password, this.createdAt, this.updatedAt}
  );

  @override
  String toString() {
    return '{id: $id, name: $name, cpf: $cpf, birthDate: $birthDate, email: $email, password: $password, createdAt: $createdAt, updatedAt: $updatedAt}';
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

  static fromJson(parsedJson) {
    Psychologist p = new Psychologist();
    p.id = parsedJson['id'];
    p.name = parsedJson['name'];
    p.cpf = parsedJson['cpf'];
    p.birthDate = parsedJson['birth_date'];
    p.email = parsedJson['email'];
    p.password = parsedJson['password'] ?? null;
    p.createdAt = DateTime.parse(parsedJson['created_at']);
    p.updatedAt = DateTime.parse(parsedJson['updated_at']);
    return p;
  }
}