import { trimHandler } from './api/trim';
import { engineHandler } from './api/engine';
import { bodyTypeHandler } from './api/body-type';
import { trimColorHandler } from './api/trim-color';
import { wheelDriveHandler } from './api/wheel-drive';
import { trimNPerformanceHandler } from './api/trim-n-performance';
import { trimSelectOptionHandler } from './api/trim-select-option';
import { trimDefaultOptionHandler } from './api/trim-default-option';
import { trimHGenuineAccessoriesHandler } from './api/trim-h-genuine-accessories';
import { savedMyChivingHandler } from './api/my-archiving-saved';
import { tempMyChivingHandler } from './api/my-archiving-temp';
import { feedMyChivingHandler } from './api/my-archiving-feed';

export const handlers = [
  ...trimHandler,
  ...wheelDriveHandler,
  ...trimSelectOptionHandler,
  ...trimNPerformanceHandler,
  ...trimHGenuineAccessoriesHandler,
  ...trimDefaultOptionHandler,
  ...trimColorHandler,
  ...engineHandler,
  ...bodyTypeHandler,
  ...savedMyChivingHandler,
  ...tempMyChivingHandler,
  ...feedMyChivingHandler,
];
