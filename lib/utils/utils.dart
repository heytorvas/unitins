formValidator(value) {
  if (value == null || value.isEmpty) {
    return 'Insira algum valor';
  }
  return null;
}