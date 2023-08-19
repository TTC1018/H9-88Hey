export function isEmailEmpty(email: string) {
  return email === '';
}

export function isPasswordEmpty(password: string) {
  return password === '';
}

export function isEmailValid(email: string, emailRegex: RegExp) {
  return emailRegex.test(email);
}
