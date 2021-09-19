class Examining {
  final int id;
  final String name;
  final String cpf;
  final DateTime birthDate;
  final String city;
  final String state;
  final int gender;
  final int schooling;

  Examining(
    this.id, this.name, this.cpf, this.birthDate, this.city, this.state, this.gender, this.schooling
  );

  @override
  String toString() {
    return 'Examining {id: $id, name: $name, cpf: $cpf, birthDate: $birthDate, city: $city, state: $state, gender: $gender, schooling: $schooling}';
  }
}