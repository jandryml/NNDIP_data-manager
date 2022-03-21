import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import './manual-item';
import '@vaadin/button/src/vaadin-button.js';

@customElement('manual-view')
export class ManualView extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-vertical-layout>
 <manual-item style="width: 100%;"></manual-item>
 <manual-item style="width: 100%;"></manual-item>
</vaadin-vertical-layout>
<vaadin-button>
  Add Manual 
</vaadin-button>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
