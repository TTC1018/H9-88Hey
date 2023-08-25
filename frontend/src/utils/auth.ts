import { PASSWORD_MIN_LENGTH } from '@/constants';

export function isEmailEmpty(email: string) {
  return email === '';
}

export function isPasswordEmpty(password: string) {
  return password === '';
}

export function isConfirmPasswordEmpty(confirmPassword: string) {
  return confirmPassword === '';
}

export function isUserNameEmpty(userName: string) {
  return userName === '';
}

export function isPasswordIncorrect(password: string, confirmPassword: string) {
  return password !== confirmPassword;
}

export function isEmailValid(email: string, emailRegex: RegExp) {
  return emailRegex.test(email);
}

export function isPasswordInvalid(password: string) {
  return password.length < PASSWORD_MIN_LENGTH;
}
