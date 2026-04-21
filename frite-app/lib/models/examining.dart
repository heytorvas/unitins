class Examining {
  int? id;
  String? name;
  String? cpf;
  String? birthDate;
  String? city;
  String? state;
  String? gender;
  String? schooling;
  DateTime? createdAt;
  DateTime? updatedAt;

  Examining({
    this.id, this.name, this.cpf, this.birthDate, this.city, this.state, this.gender, this.schooling, this.createdAt, this.updatedAt
  });

  @override
  String toString() {
    return '{id: $id, name: $name, cpf: $cpf, birthDate: $birthDate, city: $city, state: $state, gender: $gender, schooling: $schooling, createdAt: $createdAt, updatedAt: $updatedAt}';
  }

  Map<String, dynamic> toJson() {
    return {
      "name": this.name,
      "cpf": this.cpf,
      "birth_date": this.birthDate,
      "city": this.city,
      "state": this.state,
      "gender": this.gender,
      "schooling": this.schooling
    };
  }

  static fromJson(parsedJson) {
    Examining e = new Examining();
    e.id = parsedJson['id'];
    e.name = parsedJson['name'];
    e.cpf = parsedJson['cpf'];
    e.birthDate = parsedJson['birth_date'];
    e.city = parsedJson['city'];
    e.state = parsedJson['state'];
    e.gender = parsedJson['gender'];
    e.schooling = parsedJson['schooling'];
    e.createdAt = DateTime.parse(parsedJson['created_at']);
    e.updatedAt = DateTime.parse(parsedJson['updated_at']);
    return e;
  }
}