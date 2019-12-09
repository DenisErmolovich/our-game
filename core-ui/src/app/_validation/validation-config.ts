export interface ValidationConfig {
  [controlName: string]: ControlConfig;
}

export interface ControlConfig {
  error: string;
  messages: ValidationMessages;
}

export interface ValidationMessages {
  [validatorName: string]: string;
}
