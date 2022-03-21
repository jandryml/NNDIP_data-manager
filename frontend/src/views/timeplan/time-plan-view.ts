import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import './time-plan-item';
import '@vaadin/button/src/vaadin-button.js';

@customElement('time-plan-view')
export class TimePlanView extends LitElement {
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
<vaadin-vertical-layout style="width: 100%; height: 100%; padding: var(--lumo-space-m);" theme="spacing-xl">
 <vaadin-vertical-layout theme="spacing-xl">
  <time-plan-item></time-plan-item>
  <time-plan-item></time-plan-item>
  <time-plan-item></time-plan-item>
 </vaadin-vertical-layout>
 <vaadin-button>
  Add item 
 </vaadin-button>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
