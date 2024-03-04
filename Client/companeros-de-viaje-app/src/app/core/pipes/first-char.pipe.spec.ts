import { FirstCharPipe } from './first-char.pipe';

describe('FirstCharPipe', () => {
  it('create an instance', () => {
    const pipe = new FirstCharPipe();
    expect(pipe).toBeTruthy();
  });
});
